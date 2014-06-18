#adapt: "b.vcl"
	#insert break_a
	I am inserting from lower level
	#endinsert
#endadapt

	before break A
	#break: break_a
		Mr. Small.
	#endbreak
	
	after break A