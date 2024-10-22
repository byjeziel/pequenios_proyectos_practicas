const cards = $('#cards');

const templateCard = document.getElementById('template-card').content;

$(document).ready(() => {
    fetchData();
})

const fetchData = async () => {
    try {
        const res = await fetch('api.json');
        const data = await res.json();
        showProducts(data);
    } catch (error) {
        console.log(error);
    }
}

const showProducts = data => {
    data.forEach(product => {
        templateCard.querySelector('h5').textContent = product.title;
        templateCard.querySelector('p').textContent = product.price;
        templateCard.querySelector('img').setAttribute("src", product.thumbnailUrl);
        templateCard.querySelector('button').dataset.id = product.id;

        const clone = templateCard.cloneNode(true);
        fragment.appendChild(clone);
    })
    cards.append(fragment);
}
