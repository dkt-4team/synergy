
    const container = document.getElementById("container");
    const title = document.getElementById("title");
    const content = document.getElementById("content");

    function assignPost() {
        let theForm = document.assignSubmit;
        theForm.method = "post";
        theForm.enctype = "multipart/form-data";
        theForm.action = "/student/assignRegister";
        theForm.submit();
    }
