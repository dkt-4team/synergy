
    const [delId, subId] = [
      document.querySelector('#delId'),
      document.querySelector('#subId')
    ];

    function submitForm() {
        let form = document.commentSubmit;
        form.submit();
    }

    function deleteComment(id, submitNum) {
        let theForm = document.delComment;
        theForm.method = "post";
        theForm.action = `/admin/commentDelete/`;
        delId.value = id;
        subId.value = submitNum;
        theForm.submit();
    }

    function assignDownload(url) {
        console.log(url);
        window.location.href = url;
    }