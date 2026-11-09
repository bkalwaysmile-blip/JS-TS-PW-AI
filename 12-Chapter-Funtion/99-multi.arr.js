const getResult = (score) => {
  if (score > 70) return "Pass";
  return "fail";
};

console.log(getResult(78)); // "Pass"
console.log(getResult(43)); // "fail"