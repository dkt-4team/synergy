
    //여기서부터 모달창
    const notiDialog = document.querySelector('dialog#notiDialog');
    const assignDialog = document.querySelector('dialog#assignDialog');

    const [assignOpen, assignClose] = [
        document.querySelector('#assignOpen'), // btnOpen에 담김 (body button#btnOpen)
        document.querySelector('#assignClose')
    ];
    const [notiOpen,notiClose,notiModifyBtn] = [
        document.querySelector('#notiOpen'),
        document.querySelector('#notiClose'),
        document.querySelector('#notiModifyBtn'),

    ];

    // 과제 삭제
    function deleteAssignment(id){
        let theForm = document.delAssignment;
        theForm.method = "post";
        theForm.action = "/admin/assignmentDelete";
        delId.value = id;
        theForm.submit();
    }

    // 과제 데이터 전송
    function assignPost(sig) {
        let theForm = document.assignSubmit;
        let subText = document.getElementById("sub-name");
        let tit = document.getElementById("title");
        let con = document.getElementById("content");

        if (sig == "01") {
            theForm.method = "post";
            theForm.action = "/admin/assignRegister";
            theForm.enctype = "multipart/form-data";
            subText.innerText = "저장하기";
            con.value = '';
        }
    }

    // 공지 데이터 전송
    function notiPost(sig,notification) {
        console.log(notification);
        let theForm = document.notiSubmit;
        let subText = document.getElementById("sub-noti");
        let textarea = document.getElementById("noti-textarea");
        let subTitle = document.getElementById("noti-title");
        let labelOption = document.getElementById("optionList");
        theForm.method = "post";

        if (sig == "01") {
            theForm.action = "/notificationSave";
            subText.innerText = "저장하기";
            textarea.value = '';
            subTitle.value = '';
        } else if (sig == "02") {
            theForm.method = "post";
            theForm.action = `/notificationUpdate/${notification.id}`;
            textarea.value = notification.content;
            subTitle.value = notification.title;
            labelOption.value = notification.labelOption;
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
        if (evt.target.closest('.notification')!=null) {
            notiDialog.showModal();
        }
    });

