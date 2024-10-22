const footer = $("#footer");
const templateFooter = document.getElementById('template-footer').content;

const showFooter = () => {
    footer.html('');
    if(localStorage.length === 0){
        footer.html(`
            <th scope="row" colspan="5">Carrito vacío</th>
        `);
        return
    }

    let totalAmount = 0;
    let totalPrice = 0;

    for(let i = 0; i < localStorage.length; i++){
        totalAmount += JSON.parse(localStorage.getItem(localStorage.key(i))).amount;
        totalPrice += JSON.parse(localStorage.getItem(localStorage.key(i))).amount * JSON.parse(localStorage.getItem(localStorage.key(i))).price;
    }

    templateFooter.querySelectorAll('td')[0].textContent = totalAmount;
    templateFooter.querySelector('span').textContent = totalPrice;

    const clone = templateFooter.cloneNode(true);
    fragment.appendChild(clone);
    footer.append(fragment);

    const btnDeleteCart = document.getElementById('delete-cart');
    btnDeleteCart.addEventListener('click', () => {
        localStorage.clear();
        showCart();
    })
}