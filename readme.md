Given a list of positions and a list of trades match up and return trades that don't have correct positions.

Position JSON:
{"id": 1234, "symbol":"ACOR1 210416C00001000", "type": "short/long", "quantity": 10}//12

Trade JSON:
{"id": 4569, "quantity": 5, "legs":[{"symbol": "ACOR1 210416C00001000" , "type":"short/long"}]}

return back a list of extra positions and list of positions that are missing