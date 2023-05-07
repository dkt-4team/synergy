
    //여기서부터 모달창
    const notiDialog = document.querySelector('dialog#notiDialog');
    const assignDialog = document.querySelector('dialog#assignDialog');

    const [assignOpen, assignClose, assignModifyBtn] = [
        document.querySelector('#assignOpen'), // btnOpen에 담김 (body button#btnOpen)
        document.querySelector('#assignClose'),
        document.querySelector('#assignModifyBtn'),
    ];
    const [notiOpen,notiClose,notiModifyBtn] = [
        document.querySelector('#notiOpen'),
        document.querySelector('#notiClose'),
        document.querySelector('#notiModifyBtn'),

    ];

    // 과제 데이터 전송
    function assignPost(sig, todo) {
        let theForm = document.assignSubmit;
        let subText = document.getElementById("sub-name");
        let con = document.getElementById("content");

        if (sig == "01") {
            theForm.method = "post";
            theForm.action = "/admin/assignRegister";
            theForm.enctype = "multipart/form-data";
            subText.innerText = "저장하기";
            con.value = '';
        } else if (sig == "02") {
            theForm.method = "post";
            theForm.action = `/assignment/update/${todo.id}`;
            con.value = todo.content;
            subText.innerText = "수정하기";
        }
    }

    // 공지 데이터 전송
    function notiPost(sig, id) {
        let theForm = document.notiSubmit;
        let subText = document.getElementById("sub-noti");

        if (sig == "01") {
            theForm.method = "post";
            theForm.action = "/notificationSave";
            subText.innerText = "저장하기";
        } else if (sig == "02") {
            theForm.method = "post";
            theForm.method = "post";
            theForm.action = `/notificationUpdate/${id}`;
            subText.innerText = "수정하기";
        }
    }

    //   모달 메소드

    //  open
    notiOpen.addEventListener('click', function () {
        notiDialog.showModal();
    });

    assignOpen.addEventListener('click', function (evt) {
        assignDialog.showModal();
    });
    // close
    assignClose.addEventListener('click', function () {
        assignDialog.close();
    });

    notiClose.addEventListener('click', function () {
        notiDialog.close(); // close 버튼이 눌리면 닫는 함수 동작.
    });

    // 이 부분 수정해야함.

    notiModifyBtn.addEventListener('click', function (evt) {
        if (evt.target.closest('.noti-Content')!=null) {
            notiDialog.showModal();
        }
    });

    assignModifyBtn.addEventListener('click', function (evt) {
        if (evt.target.closest('.assignment')!=null) {
            assignDialog.showModal();
        }
    });

