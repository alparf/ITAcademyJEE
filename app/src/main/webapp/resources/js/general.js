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
}
