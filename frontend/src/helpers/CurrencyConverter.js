// .co.uk - GBP
// .com - USD
// .de - EUR
// .fr - EUR
// .it - EUR
// .es - EUR
// .co.jp - JPY
// .com.mx - MXN
// .com.br - BRL
// .ca - CAD

const CurrencyConverter = function(domain, convertTo, price) {
  this.domain = domain;
  this.converTo = converTo;
  this.price = price;
}

export default CurrencyConverter;
