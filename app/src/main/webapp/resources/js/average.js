window.onload = function() {
    document.getElementById("average-btn").addEventListener("click", function() {
        console.log(document.getElementById('month-count').value)
        let url = "AverageSalariesController?monthCount=" + document.getElementById('month-count').value;
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
    let container = document.getElementById("salaries-container");
    let averageSalaryList = "";
    for (const [key, value] of Object.entries(data)) {
        averageSalaryList += getSalaryInner(key, value);
    }
    removeAllChildNodes(container);
    container.innerHTML = averageSalaryList;
}

function getSalaryInner(name, salary) {
    return `
        <div class="salary_inner">
            <span>${name}</span>
            <span>${salary / 100.0} BYN</span>
        </div>`
}

function removeAllChildNodes(parent) {
    while (parent.firstChild) {
        parent.removeChild(parent.firstChild);
    }
}