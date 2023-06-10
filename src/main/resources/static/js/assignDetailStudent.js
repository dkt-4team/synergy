
    const container = document.getElementById("container");
    const title = document.getElementById("title");
    const content = document.getElementById("content");

    function assignPost(id) {
        let theForm = document.assignSubmit;
        theForm.method = "post";
        theForm.enctype = "multipart/form-data";
        theForm.action = `/student/assignRegister/${id}`;
        theForm.submit();
    }

    function assignDownload(url) {
        window.location.href = url;
    }
