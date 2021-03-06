// file ComponentUtil.java 

// package <default>

// file ComponentUtil.java  

import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URI;

public class ComponentUtil extends Object{

    public static  Frame getParent( Component theComponent ) {
        // this finds the topmost ancestor parent of a component,;;
        // so that we can give that parent to dialog constructors...;;
        Component currParent = theComponent;
        Frame theFrame = null;
        while ( currParent != null ) {
            if ( currParent instanceof Frame ) {
                theFrame = (Frame) currParent;
                break;
            };
            currParent = currParent.getParent();
        };
        return theFrame;
    } // getParent

    /**
     * Sets up a dialog box that lets the user choose a file.
     * Have to copy in the ComponentUtil.java class to use the "getParent"
     * which is necessary for our FileDialog 
     */
    static URL getSomeOldFile( Component C ) {
        File myF = null;
        URL url = null;
        Frame dFrame = ComponentUtil.getParent( C );
        FileDialog myFDialog = new FileDialog( dFrame, "hiya" );
        /* have to "import java.io.*;" to use FileDialog */;
        /* note: can append ",FileDialog.LOAD)" or ",FileDialog.SAVE)" to FileDialog constructor */;
        myFDialog.setVisible(true); // show(); deprecated
        try  {
            myF = new File( myFDialog.getDirectory(), myFDialog.getFile() );
        } catch( Exception exc ) {
            System.out.println( "Error opening file:" + exc );
        };
        try {
            url = myF.toURI().toURL();
        } catch(Exception e) {
            System.out.println( "Error converting file to URL:"+ myF+"\n" + e );
        }
        return url;
    } // getSomeOldFile

    /**
     * When given a file, this reads all its contents and puts them into a string 
     */;
    static  String readStringFromFile(URL theFile) {
        System.out.println("Reading from:" + theFile);
        String myReturnString;
        if ( theFile == null ) {
            myReturnString = "No File was opened";
        }
        else {
            InputStream fstream;
            try {
                fstream = theFile.openStream();
            }catch(IOException ioe) {
                ioe.printStackTrace();
                return "Error opening file: "+ theFile;
            }
            //This code uses a BufferedReader and can take whole lines at once
            BufferedReader in = new BufferedReader(new InputStreamReader(fstream));

            // okay, we're ready to go...

            System.out.println("File: " + theFile);

            try {
                myReturnString = in.readLine();
                String line = in.readLine();
                while(line != null) {
                    myReturnString += "\n" + line;
                    line = in.readLine();
                }
            }catch(IOException ioe) {
                ioe.printStackTrace();
                return "Error reading file: "+ theFile;
            }
        };
        return myReturnString;
    } // readStringFromFile

} // class ComponentUtil

