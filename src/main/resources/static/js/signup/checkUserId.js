const checkUsernameBtn = document.getElementById("checkUserId");
const usernameInput = document.getElementById("user_id_input");
checkUsernameBtn.addEventListener('click', () => {
    const username = usernameInput.value;
    const xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.status === 200) {
            const response = xhr.responseText;
            if (response === 'true') {
                alert("사용할 수 있는 아이디입니다.");
            } else {
                alert("중복된 아이디입니다.");
                document.getElementById("user_id_input").value="";
            }
        } else {
            console.error(xhr.statusText);
        }
    };
    xhr.open('POST', '/check/userId');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(username);
})