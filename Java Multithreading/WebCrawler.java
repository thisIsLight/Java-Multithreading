/*
 * Description: Implement a web crawler that starts from a seed URL, crawls web pages concurrently, and extracts links up to a certain depth.
Skills Practiced:
Thread management (ExecutorService, BlockingQueue)
Synchronization for shared data (visited URLs)
Networking in Java (HttpURLConnection)
Features:
Input: Starting URL and maximum depth to crawl.
Use a thread pool to manage multiple crawling threads.
Use a ConcurrentHashMap or BlockingQueue to keep track of visited URLs to avoid duplication.
Use Future to collect crawled data like title, meta information, and links.
Optional Enhancements:
Implement politeness rules (time delay between requests to the same domain).
Store results in a database.
Add a feature to download images or other media.
 */

public class WebCrawler {
    
}
