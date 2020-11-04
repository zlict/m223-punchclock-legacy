const URL = 'http://localhost:8081';
let categories = [];

const createCategory = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const category = {};
    category['name'] = formData.get('name');
    fetch(`${URL}/categories`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(category)
    }).then((result) => {
        result.json().then((entry) => {
            categories.push(entry);
            renderCategories();
        });
    });
};

const deleteCategory = (id) => {
    fetch(`${URL}/categories/${id}`, {
        method: 'DELETE'
    }).then(() => {
        indexCategories();
    });
}

const indexCategories = () => {
    fetch(`${URL}/categories`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            categories = result;
            renderCategories();
        });
    });
    renderCategories();
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

const renderCategories = () => {
    const display = document.querySelector('#categoryDisplay');
    display.innerHTML = '';
    categories.forEach((category) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(category.id));
        row.appendChild(createCell(category.name));
        row.appendChild(createButton('delete', () => deleteCategory(category.id) ));
        display.appendChild(row);
    });
};

document.addEventListener('DOMContentLoaded', function(){
    const createCategoryForm = document.querySelector('#createCategoryForm');
    createCategoryForm.addEventListener('submit', createCategory);
    indexCategories();
});