#output "output.txt"
#adapt: "a.vcl"
	#insert-before break_a
	 	This is before the break.
	#endinsert
	#insert break_a
	 	This is inside break.
	#endinsert
	#insert-after break_a
	 	This is after the break.
	#endinsert
#endadapt