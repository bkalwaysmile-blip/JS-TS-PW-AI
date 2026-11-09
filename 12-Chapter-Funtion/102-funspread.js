function hasError(...codes) {
  return codes.some(c => c >= 400);
}

let responseCodes = [200, 201, 404];

console.log(hasError(...responseCodes)); // true