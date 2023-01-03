function validateForm()  {
    const password = document.querySelector('#password').value;
    const repeatPassword = document.querySelector('#repeat-password').value;

    if (password != repeatPassword && paassword.length < 8) {
        return false;
    } else {
        return true;
    }
}
