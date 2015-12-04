package sample.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "article")
@Entity
public class Article {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    //private List<String> tags = new ArrayList<String>();

    public Article(){
    	//id = Math.abs(new Random().nextLong());
    }

	public Article(String title, String... tags){
		//id = Math.abs(new Random().nextLong());
    	this.title = title;
    	//this.tags.addAll(Arrays.asList(tags));
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	/*public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}*/

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + /*" tags=" + tags +*/ "]";
	}

}
