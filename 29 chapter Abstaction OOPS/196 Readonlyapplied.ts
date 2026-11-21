// Readonly

interface Point{
    readonly x:number;
    readonly y:number;
}

const point : Point = {
    x:10,
    y:10,
}
console.log(point)

// point.x = 15
console.log(point)