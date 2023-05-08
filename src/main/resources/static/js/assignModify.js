
    const assignDialog = document.querySelector('dialog#assignDialog');

    const [assignModifyBtn, assignClose] = [
        document.querySelector('#assignModifyBtn'),
        document.querySelector('#assignClose')
    ];

    // 과제 데이터 전송
    function assignPost(sig, id, title, content) {
        let theForm = document.assignSubmit;
        let subText = document.getElementById("sub-name");
        let tit = document.getElementById("title");
        let con = document.getElementById("content");

        if (sig == "02") {
            theForm.method = "post";
            theForm.action = `/admin/assignmentUpdate/${id}`;
            theForm.enctype = "multipart/form-data";
            tit.value = title;
            con.value = content;
            subText.innerText = "수정하기";
        }
    }

    assignModifyBtn.addEventListener('click', function (evt) {
        // if (evt.target.closest('.assignmentDetail')!=null) {
        assignDialog.showModal();
        // }
    });

    assignClose.addEventListener('click', function () {
        assignDialog.close();
    });