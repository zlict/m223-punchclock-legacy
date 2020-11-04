const URL = 'http://localhost:8081';
let entries = [];
let categories = [];
let formdata = null

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    formdata = formData;
    console.log(formData);
    console.log(e.target);
    console.log(formData.get('category'));
    entry['checkIn'] = dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));
    entry['category'] = { "id": formData.get('category')};
    if (entry['checkIn'] > entry['checkOut']) {
        document.getElementById("checkIn").style.backgroundColor = "red";
        document.getElementById("checkOut").style.backgroundColor = "red";
    } else {
        fetch(`${URL}/entries`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(entry)
        }).then((result) => {
            result.json().then((entry) => {
                entries.push(entry);
                renderEntries();
            });
        });
    }
};

const deleteEntry = (id) => {
    fetch(`${URL}/entries/${id}`, {
        method: 'DELETE'
    }).then(() => {
        indexEntries();
    });
}

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            entries = result;
            renderEntries();
        });
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const createButton = (text, method) => {
    const button = document.createElement("button");
    button.innerText = text;
    button.onclick = method;

    return button
}

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createCell(entry.category.name))
        row.appendChild(createButton('delete', () => deleteEntry(entry.id)));
        display.appendChild(row);
    });
};

const indexCategories = () => {
    fetch(`${URL}/categories`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            categories = result;
            initializeCategories();
        });
    });
};

const initializeCategories = () => {
    var select = document.createElement("select");
    select.name = "category";
    select.id = "category"


    for (const val of categories) {
        var option = document.createElement("option");
        option.value = val.id;
        option.text = val.name;
        select.appendChild(option);
    }

    var label = document.createElement("label");
    label.innerHTML = "Category: "
    label.htmlFor = "category";

    document.getElementById("categoryContainer").appendChild(label).appendChild(select);

}

document.addEventListener('DOMContentLoaded', function () {
    const createEntryForm = document.querySelector('#createEntryForm');
    indexCategories();
    createEntryForm.addEventListener('submit', createEntry);
    indexEntries();
});