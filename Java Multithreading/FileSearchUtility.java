/*
Description: 
Create a multithreaded program that searches for a specific keyword or phrase across multiple files and directories simultaneously. 
Each thread can search a different file or directory.

Skills Practiced:

Thread pools (ExecutorService), Callable, Future
Handling large amounts of I/O-bound tasks
File I/O with concurrency


Features:

Input: Directory path, keyword, file type filters.

Output: A list of files containing the keyword and the line numbers where the keyword is found.
Use Future to gather the results from each thread.
Handle multiple file types (e.g., .txt, .java, .xml).


Optional Enhancements:

Implement a progress bar to show the progress of the search.
Handle very large directories with thousands of files efficiently.

*/

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FileSearchUtility {

    public static void main(String args[]) throws Exception{
        List<Files> files = new ArrayList<Files>();
        files.add(new Files("Fueled by his childhood fascination with computers, Bagaria identified a problem with the overwhelming number of messaging apps. He envisioned Texts.com as a personal solution, aggregating all his messaging needs into one convenient platform. After validating the idea with friends and receiving positive feedback, he built a prototype. Word-of-mouth spread quickly on Twitter, and Texts.com gained traction among industry experts."));
        files.add(new Files("The acquisition of Texts.com was a whirlwind of emotions for Bagaria. He initially shielded his parents from the exact amount but, News outlets soon revealed the details, leaving his parents undoubtedly proud and secure."));
        files.add(new Files("Texts.com, under Automattic's ownership, has the potential to revolutionize how we manage our digital communication. Kishan Bagaria's story serves as a testament to the power of innovation and the boundless possibilities within the tech world. He showed that a college degree is optional to learn the skills when we have access to the Internet."));
        files.add(new Files("Acer’s new Super Series range of TVs pushes the boundaries of picture quality, design, sound, and performance at a very attractive price. Take the new 55-inch UHD Smart QLED TV (AR55QDXGU2875) for example, a superb 55 inch TV, which I’ve been testing for the last 10 days with multiple content sources (OTT apps, 4K digital media, satellite TV, and casting from mobile devices). The TV itself is slim, frameless, and looks elegant. The Ultra QLED display provides sharp, bright and vivid realistic images, no matter the content. Sound is an area I feel where Acer has come up trumps, providing Hi-Fi sound via 80W Pro speakers with Gigabit Bass. Built-in Dolby Vision and Dolby Atmos ensure a blissful visual and aural experience, while Google TV provides all the apps/OTT platforms you require. amazon.in flipkart.com"));
        files.add(new Files("Ultimate Ears (UE) is known for its speakers that come in compact sizes yet belt out powerful, clear, and dynamic audio. Their new Miniroll is proof that great sound can come in small packages as well. The Miniroll is small, light, and comes with a 12-hour battery and IP67 resistance (survives water, dust, and 1.2m drops). It’s also made with recycled plastic and can be paired with more Minirolls for even more sound! ultimateears.com"));
        files.add(new Files("The aH-1 is a unique helmet that provides premium head-protection and can also collapse in size (upto 90%) for ease of carrying. The patented, pneumatic structural system inflates in less than 30 seconds with included electric pump. The Made in Switzerland helmet comes in three sizes and is engineered to keep you head cool and safe. ventete.com"));
    
        List<Callable<Boolean>> tasks = new ArrayList<>();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        String targetWord = "you";

        for(int i=0;i<files.size();i++){
            var data = files.get(i).Content;
            tasks.add(() -> {
                var found = false;
                String[] array = data.split(" ");
                for(int j=0; j<array.length;j++){
                    if(targetWord.equals(array[j])){
                        return true;
                    }
                }
                return found;
            });
        }
        
        List<Future<Boolean>> results = executor.invokeAll(tasks);
        List<String> res = new ArrayList<>();
        int counter = 1;
        for(var item : results){
            System.out.println(item.get());
            if(item.get()){
                res.add("File Number : " + counter);
            }
            counter++;
        }

        System.out.println(res.toString());
        executor.shutdown();
    } 
    


    
}

class Files{
    public String Content;
    public Files(String content) {
        super();
        Content = content;
    }
}


