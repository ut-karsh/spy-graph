///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             InvalidAreaFileException.java
// Semester:         CS 367 Summer 2017
//
// Author:           Utkarsh Maheshwari umaheshwari@wisc.edu
// CS Login:         maheshwari
// Lecturer's Name:  Meenakshi Syamkumar
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Jared Akers
// Email:            jakers@wisc.edu
// CS Login:         akers
// Lecturer's Name:  Meenakshi Syamkumar
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
public class InvalidAreaFileException extends Exception {
    public static final String example =
"NODES\na\nb\nc\nd\ne\nf\n"+
"EDGES\n"+"a b 1\n"+"b c 2\n"+"c d 1\n"+"d e 3\n"+"e f 1\n"+"f a 1\n"+"a c 4\n"+"a d 20\n";
    public InvalidAreaFileException(String badLine) {
        super("InvalidAreaFilenameException\n"+
        "Example Format for 6 vertices and 8 edges:\n"+example);
        System.out.println(badLine+ " is invalid as vertex label or edge descriptor");
    }
}
