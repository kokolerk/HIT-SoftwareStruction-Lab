package P2;

public class Person {
   
	
	// Abstraction function:
    // the vertex in graph .
    //
    // Representation invariant:
    // name is non-null and non empty string. 
    // Any two person who has equal name are the same person
    //
    // Safety from rep exposure
    // Fields are modified by keyword private and final. And only getter
    // method without setter method so clients can not change the object.
	    private final String name;
		
	    public Person(String name) {
	    	this.name=name;
	    }
		public String getname() {
			return name;
		}
		@Override
	    public boolean equals(Object that) {
	        if(that==null) return false;
			if (that==this) {
	            return true;
	        }
	        if (that instanceof Person) {
	            Person anotherThat = (Person) that;
	            return this.getname().equals(anotherThat.getname());
	        }
	        return false;
	    }

	    @Override
	    public int hashCode() {
	        int result = 17;
	        result = 31 * result + name.hashCode();
	        return result;
	    }
}
