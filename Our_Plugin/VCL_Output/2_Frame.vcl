#output ?@method_0?"Buffer.java"

public ?@variable_0? slice ( ) { int pos = this.position ( ) ; int lim = this.limit ( ) ; assert ( pos <= lim ) ; int rem = ( pos <= lim ? lim - pos : 0 ) ; int off = ( pos << ?@variable_1? ) + offset ; assert ( off >= 0 ) ; return new ?@variable_2? ( bb , - 1 , 0 , rem , rem , off ) ; } 