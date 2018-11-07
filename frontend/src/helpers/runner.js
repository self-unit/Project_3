const CurrencyHelper = require('./CurrencyHelper');

const currency = new CurrencyHelper();
console.log(currency.convert('.co.uk', 'USD', 55));
// console.log(currency.getCurrencyFromDomain('.co.uk'));
