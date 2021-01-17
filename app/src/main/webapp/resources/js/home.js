window.onload=function() {
      document.getElementById("addUser-btn").addEventListener("click", function() {
        document.getElementById("addUser").hidden = false;
        document.getElementById("userList").hidden = true;
        document.getElementById("coachList").hidden = true;
        document.getElementById("salary").hidden = true;
    }, false);

    document.getElementById("userList-btn").addEventListener("click", function() {
        document.getElementById("addUser").hidden = true;
        document.getElementById("userList").hidden = false;
        document.getElementById("coachList").hidden = true;
        document.getElementById("salary").hidden = true;
    }, false);

    document.getElementById("coachList-btn").addEventListener("click", function() {
        document.getElementById("addUser").hidden = true;
        document.getElementById("userList").hidden = true;
        document.getElementById("coachList").hidden = false;
        document.getElementById("salary").hidden = true;
    }, false);

    document.getElementById("salary-btn").addEventListener("click", function() {
        document.getElementById("addUser").hidden = true;
        document.getElementById("userList").hidden = true;
        document.getElementById("coachList").hidden = true;
        document.getElementById("salary").hidden = false;
    }, false);

    document.getElementById("userList-btn").addEventListener("click", function() {
        var url = "UserListController";
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                showData(data);
            });
    });
}

function showData(data) {
    var container = document.getElementById("userList");
    removeAllChildNodes(container);
    for (const [key, value] of Object.entries(data)) {
      console.log(`${key}: ${value}`);
      createContainer(key, value, container);
    }
}

function createContainer(name, salary, container) {
    var inner = document.createElement("div");
    var name_span = document.createElement("span");
    name_span.innerHTML = name;
    var salary_span = document.createElement("span");
    salary_span.innerHTML = (salary / 100.0) + " BYN";
    inner.classList.add("salary_inner");
    inner.appendChild(name_span);
    inner.appendChild(salary_span);
    container.appendChild(inner);
}


