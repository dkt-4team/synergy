
    const container = document.getElementById("container");
    const title = document.getElementById("title");
    const content = document.getElementById("content");
    var assignId;

    function assignPost() {
        let theForm = document.assignSubmit;
        theForm.refUserId = assignId;
        console.log(theForm.refUserId);
        theForm.method = "post";
        theForm.enctype = "multipart/form-data";
        theForm.action = "/studentAssignRegister";
        theForm.submit();
    }

    function getAssign(assign){
        container.style.display = "block";
        title.innerText = assign.title;
        content.innerText = assign.content;

		assignId = assign.id;
    }