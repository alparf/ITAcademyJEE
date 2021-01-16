window.onload = function() {
    document.getElementById("average-btn").addEventListener("click", function() {
        console.log(document.getElementById('month-count').value)
        var url = "AverageSalariesController?monthCount=" + document.getElementById('month-count').value;
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
    var container = document.getElementById("salaries-container");
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

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}