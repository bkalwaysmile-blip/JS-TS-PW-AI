class Person { // (CAB)
    // Attributes | Instance variables |  Data members | Properties
    #name;
    #age;
    #rollNo;
    #phoneNo;

    // Constructor, Attributes, Behaviour - CAB


    // # Constructor
    //  **A special function that runs automatically, 
    // once, at the moment an object is born.**
    constructor() {
        console.log("I will be called whenever an object is created. ")
    }



    // Behaviour | Methods | Members Functions | 
    eat(){}
    sleep(){}
}


// # and we are not using function key in class for the f(n)

// CAB -> Class has the A, B

const pramod = new Person();
// Object -> new Person();
// Object Ref -> pramod

const amit = new Person();