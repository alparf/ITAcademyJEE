window.onload=function() {
      document.getElementById("addUser-btn").addEventListener("click", function() {
        document.getElementById("addUser").hidden = false;
        document.getElementById("userList").hidden = true;
        document.getElementById("coachList").hidden = true;
      }, false);

    document.getElementById("userList-btn").addEventListener("click", function() {
        document.getElementById("addUser").hidden = true;
        document.getElementById("userList").hidden = false;
        document.getElementById("coachList").hidden = true;
        getItemList("UserController", buildUserList);
    }, false);

    document.getElementById("coachList-btn").addEventListener("click", function() {
        document.getElementById("addUser").hidden = true;
        document.getElementById("userList").hidden = true;
        document.getElementById("coachList").hidden = false;
        getItemList("CoachController", buildCoachList);
    }, false);
}

function getUser(user) {
    return `
        <div class="user-list_form">
            <span>${user.fio}</span>
            <span>${user.age}</span>
            <span>${user.userName}</span>
            <span>${user.userType}</span>
            <a class="btn" href="#" onclick="removeUser(${user.id})">Remove</a>
        </div>`
}

function getCoach(coach, salaryList) {
    return `
        <div class="coach_form">
            <span name="coachName" class="item">${coach.fio}</span>
            <div class="coach_form_inner">
                <input id="coach${coach.id}" name="salary" type="text" required/>
                <span>BYN</span>
                <a class="btn" href="#" onclick="addSalary(${coach.id})">Add Salary</a>
            </div>
        </div>
        <div>
            ${salaryList}
        </div>`
}

function getItemList(url, builder) {
    fetch(url)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            return builder(data);
        });
}

function removeUser(id) {
    let coachId = "" + id;
    let data = {
        userIdToRemove : coachId
    }
    fetch("UserController", {
        method : "DELETE",
        body : JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    }).
    then((response) => {
        getItemList("UserController", buildUserList);
    })
}

function addSalary(id) {
    let salary = document.getElementById("coach" + id).value;
    let coachId = "" + id;
    let data = {
        coachId : coachId,
        salary : salary
    }
    fetch("SalaryController", {
        method : "POST",
        body : JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    }).
    then((response) => {
        getItemList("CoachController", buildCoachList);
    })
}

function buildUserList(data) {
    let container = document.getElementById("userList");
    let userList = "<h3>User List</h3>";
    for (let user of data) {
        userList += getUser(user);
    }
    removeAllChildNodes(container);
    container.innerHTML = userList;
}

function buildCoachList(data) {
    let container = document.getElementById("coachList");
    let coachList = "<h3>Coach List</h3>";
    for (let coach of data) {
        let salaryList = [];
        for (let salary of coach.salaries) {
            salaryList.push(salary.value)
        }
        coachList += getCoach(coach.user, salaryList);
    }
    removeAllChildNodes(container);
    container.innerHTML = coachList;
}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}