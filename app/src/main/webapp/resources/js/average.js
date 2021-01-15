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

}
