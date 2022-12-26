$(document).ready(() => {
	const dashboard = async () => {
		
	let data = await fetch("/api/v1/product/statistics/month",
		{
			method: "POST",
			body: JSON.stringify(
				{ year: 2022, month: 12 })
			, headers: {
				'Content-Type': 'application/json'
			}
		}
	).then(data => data.json()).catch(e => console.log(e));
	
	data = data.map(item => {
		item.parentId = item.brand.id
		return item
	})

	console.log(data)
	// prepare the data
	var source =
	{
		dataType: "json",
		dataFields: [
			{ name: 'id', type: 'string' },
			{ name: 'name', type: 'string' },
			{ name: 'image', type: 'string' },
			{ name: 'price', type: 'number' },
			{ name: 'quantityPrice', type: 'quantityPrice' },
			{ name: 'totalPrice', type: 'number' },
			{ name: 'brandId', type: 'number' },
			{ name: 'categories', type: 'array' },
			{ name: 'orderDate', type: 'string' },
			{ name: 'orderUpdate', type: 'string' },
			{ name: 'status', type: 'string' }
		],
		hierarchy:
		{
			keyDataField: { name: 'brandId' },
			parentDataField: { name: 'brandId' }
		},
		id: 'brandId',
		localData: data
	};
	var dataAdapter = new $.jqx.dataAdapter(source);
	// create Tree Grid
	$("#dashboard-grid").jqxTreeGrid(
		{
			width: 'auto',
			source: dataAdapter,
			ready: function() {
				$("#dashboard-grid").jqxTreeGrid('expandRow', '2');
			},
			columns: [
				{ text: 'Tên sản phẩm', dataField: 'name', width: 150 },
				{ text: 'Giá', dataField: 'price', width: 150 },
				{ text: 'Số lượng', dataField: 'quantityPrice', width: 200 },
				{ text: 'Tổng tiền', dataField: 'totalPrice', width: 200 },
				{ text: 'Tổng tiền', dataField: 'brand', width: 200 },
			]
		});
	}
	dashboard()
})
