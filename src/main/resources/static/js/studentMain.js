
    const dialog = document.querySelector('dialog#todoDialog');
    const noDialog = document.querySelector('dialog#noDialog');


    const [addOpen, editOpen, btnClose, noOpen, noClose, delId] = [
        document.querySelector('#addOpen'),
        document.querySelector('#editOpen'),
        document.querySelector('#btnClose'),
        document.querySelector('#noOpen'),
        document.querySelector('#noClose'),
        document.querySelector('#delId'),
    ];

    function deleteTodo(id){
        let theForm = document.delTodo;
        theForm.method = "post";
        theForm.action = "/todo/delete";
        delId.value = id;
        theForm.submit();
    }

// 모달 재사용을 위한 메소드
    function getPost(sig, id, content, end) {
        let theForm = document.frmSubmit;
        let subText = document.getElementById("sub-name");
        let con = document.getElementById("content");
        let endDate = document.getElementById("end_date");
        theForm.method = "post";

        if (sig == "01") {
            theForm.action = "/todo/insert";
            subText.innerText = "저장하기";
            con.value = '';
        } else if (sig == "02") {
            theForm.action = `/todo/update/${id}`;
            con.value = content;
            endDate.value = end.split('|')[1].split(" ")[0];
            subText.innerText = "수정하기";
        }
    }

// 공지사항 모달에 값을 작성하는 메소드
    function getNotification(notification){
      let subText = document.getElementById("no-title");
      let con = document.getElementById("no-content");
      subText.innerText = "[" + notification.labelOption + "] " + notification.title;
      con.innerText = notification.content;
    }

// 모달 open close 제어 메소드
    addOpen.addEventListener('click', function () {
        dialog.showModal();
    });

    editOpen.addEventListener('click', function (evt) {
        if(evt.target.closest(".todo")!=null)
            dialog.showModal();
    });

    noOpen.addEventListener('click', function (evt) {
         if(evt.target.closest(".gong-content")!=null)
            noDialog.showModal();
    });

    btnClose.addEventListener('click', function () {
        dialog.close();
    });

    noClose.addEventListener('click', function () {
        noDialog.close();
    });