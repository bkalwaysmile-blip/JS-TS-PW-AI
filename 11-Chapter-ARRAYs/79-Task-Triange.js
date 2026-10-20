const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

rl.question("Enter three sides separated by spaces: ", function(input) {
  const [a, b, c] = input.trim().split(/\s+/).map(Number);

  if (a === b && b === c) {
    console.log("Equilateral");
  } else if (a === b || b === c || a === c) {
    console.log("Isosceles");
  } else {
    console.log("Scalene");
  }

  rl.close();
});