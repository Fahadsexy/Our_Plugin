% SPC File 

#set className = "Float"
#set className_ = "float"

#adapt: "[T]Buffer.vcl"

#select className
	#option Float
		#insert breakFloat
			This text has been added from the breakFloat flag
		#endinsert
	#endoption
#endselect

#endadapt