const templateCart = document.getElementById('template-cart').content;
const items = $("#items");

$('#cards').on('click', e => {
    addCart(e);
})

$("#items").on('click', e => {
    btnAction(e);
})

const addCart = e => {
    if(e.target.classList.contains('btn-dark')){
        setCart(e.target.parentElement);
    }
    e.stopPropagation();
}

const saveProduct = (key, value) => { localStorage.setItem(key, value) };

const saveProducts = product => {
    saveProduct(product.id, JSON.stringify(product));
}

const setCart = obj => {
    const product = {
        id: obj.querySelector('.btn-dark').dataset.id,
        title: obj.querySelector('h5').textContent,
        price: obj.querySelector('p').textContent,
        amount: 1
    };

    console.log(JSON.parse(localStorage.getItem(product.id)));

    if (JSON.parse(localStorage.getItem(product.id))){
        product.amount = JSON.parse(localStorage.getItem(product.id)).amount + 1;
    }

    saveProduct(product.id, JSON.stringify(product));

    showCart();
}

const showCart = () => {
    items.html('');
    for(let i = 0; i < localStorage.length; i++){
        let producto2 = JSON.parse(localStorage.getItem(localStorage.key(i)));
        templateCart.querySelector('th').textContent = producto2.id;
        templateCart.querySelectorAll('td')[0].textContent = producto2.title;
        templateCart.querySelectorAll('td')[1].textContent = producto2.amount;
        templateCart.querySelector('.btn-info').dataset.id = producto2.id;
        templateCart.querySelector('.btn-danger').dataset.id = producto2.id;
        templateCart.querySelector('span').textContent = producto2.amount * producto2.price;
    
        const clone = templateCart.cloneNode(true);
        fragment.appendChild(clone);
    }
    items.append(fragment);
    showFooter();
}

const btnAction = e => {
    if(e.target.classList.contains('btn-info')){
        const product = JSON.parse(localStorage.getItem(e.target.dataset.id));
        product.amount++;
        saveProduct(product.id, JSON.stringify(product));
        showCart();
    }

    if(e.target.classList.contains('btn-danger')){
        const product = JSON.parse(localStorage.getItem(e.target.dataset.id));
        product.amount--;
        saveProduct(product.id, JSON.stringify(product));
        if(product.amount === 0){
            localStorage.removeItem(product.id);
        }
        showCart();
    }

    e.stopPropagation();
}