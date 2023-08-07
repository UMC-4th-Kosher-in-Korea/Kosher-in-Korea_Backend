// 공통변수
const classId = document.querySelector("input[name=class-id]").value;
const userId = document.querySelector("input[name=user-id]").value;

// 아임포트 결제
const goPayment = () => {
    console.clear();
    console.log('goPayment() invoked.');

    event.preventDefault();

    const productName = $('.class-title').text();    // 상품정보

    const name = document.querySelector("input[name=buyer_name]").value;
    const tel = document.querySelector("input[name=buyer_tel]").value;
    const email = document.querySelector("input[name=buyer_email]").value;

    const amount = parseInt($('#total-amount').text().substring(0, $('#total-amount').text().length - 1).replace(",", "")); // 금액
    const pg = document.querySelector("input[name=pg]:checked").value;
    console.log(pg);

    const IMP = window.IMP; // 생략 가능
    IMP.init("imp26386047"); // Example: imp00000000
    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay(
        {
            // param
            pg: pg,
            pay_method: "card",
            merchant_uid: "order_" + new Date().getTime(),
            name: productName,
            amount: amount,
            buyer_email: email,
            buyer_name: name,
            buyer_tel: tel
        },
        function (rsp) {
            console.log(rsp);

            if(rsp.success) {

                verifyAndSavePayInfo(rsp.imp_uid);

            } else {
                alert('처리 중 오류가 발생하였습니다. 다시 시도해 주세요');

            } // if-else

        } // function(rsp)

    ); // .request_pay
} // goPayment

// 결제검증 후 DB업데이트
const verifyAndSavePayInfo = (imp_uid) => {
    const couponId = document.querySelector("input[name=select]:checked").value;

    const params = {
        "classId": classId,
        "userId": userId,
        "couponId": couponId
    }

    $.ajax({

        type: "POST",
        url: "/pay/verify/" + imp_uid,
        data: params,
        success: data => {
            console.log(data);

            if(data.data.result === "SUCCESS") {
                location.href = "/pay/succeeded/" + data.data.orderNum;
            } else {
                alert('처리 중 오류가 발생하였습니다. 다시 시도해 주세요');
            } // if-else
        } // success

    }) // .ajax

} // savePayInfo

// 체크박스 전체선택
const selectAll = (selectAll) => {
    const checkboxes = document.querySelectorAll('input[name="agmt"]');

    checkboxes.forEach((checkbox) => {
        checkbox.checked = selectAll.checked
    }); // forEach

} // selectAll

// 하나라도 체크 해제되면 전체선택 해제
const checkSelectAll = (checkbox) => {
    const selectall = document.querySelector('#agmt-all');

    if (checkbox.checked === false) {
        selectall.checked = false;
    } // if

} // checkSelectAll