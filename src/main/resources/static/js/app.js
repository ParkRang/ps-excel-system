async function createExcel() {

    const response = await fetch("/api/excel", {
        method: "POST"
    });

    if (response.ok) {
        loadExcels();
    }
}

async function loadExcels() {

    const response = await fetch("/api/excel");

    const excels = await response.json();

    const tableBody =
        document.getElementById("excelTableBody");

    tableBody.innerHTML = "";

    excels.forEach(excel => {

        const row = `
            <tr>
                <td>${excel.id}</td>
                <td>${excel.status}</td>
                <td>${excel.requestedAt ?? ''}</td>
                <td>${excel.startedAt ?? ''}</td>
                <td>${excel.finishedAt ?? ''}</td>
                <td>${excel.filepath ?? ''}</td>
            </tr>
        `;

        tableBody.innerHTML += row;
    });
}

loadExcels();

setInterval(() => {
    loadExcels();
}, 2000);