#output ?@method_0?"Buffer.java"

?@variable_0? ( ByteBuffer bb ) { super ( - 1 , 0 , bb.remaining ( ) >> 2 , bb.remaining ( ) >> 2 ) ; this.bb = bb ; int cap = this.capacity ( ) ; this.limit ( cap ) ; int pos = this.position ( ) ; assert ( pos <= cap ) ; offset = pos ; } 