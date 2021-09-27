function table(x) { // Prints column x of a (1..10) multiplication table
    for (let j=1; j<11; j++)
        console.log("%d * %d = %d", x, j, x*j);
    console.log("");
}

function allTables() {
    for (let i=1; i<11; i++)
        table(i);
}
table(5, 1, 1);