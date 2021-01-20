window.onload = function() {
    document.getElementById("average-btn").addEventListener("click", function() {
        console.log(document.getElementById('month-count').value)
        var url = "AverageSalariesController?monthCount=" + document.getElementById('month-count').value;
        fetch(url)
            .then((response) => {
                return response.json();
            })
            .then((data) => {
                build(data);
            });
    });
}

function build(data) {
    var container = document.getElementById("salaries-container");
    var averageSalaryList = "";
    removeAllChildNodes(container);
    for (const [key, value] of Object.entries(data)) {
        averageSalaryList += getSalaryInner(key, value);
    }
    container.innerHTML = averageSalaryList;
}

function getSalaryInner(name, salary) {
    return `
        <div class="salary_inner">
            <span>${name}</span>
            <span>${salary / 100.0} BYN</span>
        </div>
    `
}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}