$(document).ready(() => {
	let flagMonth = true;
	let flagViewData = 'product'
	const formMonth = document.querySelector('.input-date-month')
	const formDay = document.querySelector('.input-date-day')
	const btnViewMonth = document.querySelector('.btn-view-month')
	const btnViewDay = document.querySelector('.btn-view-day')

	const selectorYear = document.querySelector('#year')
	const selectorMonth = document.querySelector('#month')
	const inputStartDate = document.querySelector('#startDate')
	const inputEndDate = document.querySelector('#endDate')

	const btnSearch = document.querySelector('#btn-search')
	const option = document.querySelector('#option')

	const btnViewProduct = document.querySelector('.btn-view-product')
	const btnViewBrand = document.querySelector('.btn-view-brand')
	const btnViewCategory = document.querySelector('.btn-view-category')
	const btnExpandAll = document.querySelector('.btn-view-expand-all')
	const btnCollapseAll = document.querySelector('.btn-view-collapse-all')

	const formatDateYMD = (date) => {
		let month = (date.getMonth() + 1) >= 10 ? date.getMonth() + 1 : `0${date.getMonth() + 1}`
		let day = (date.getDate()) >= 10 ? date.getDate() : `0${date.getDate()}`
		return `${date.getFullYear()}-${month}-${day}`
	}
	const enPriceVnd = (num) => {
		if (num) {
			return num.toLocaleString();
		}
	}

	btnViewProduct.onclick = () => {
		if (flagViewData !== 'product') {
			flagViewData = 'product'
			loadButtonViewData()
			searchViewData()
		}
	}

	btnViewBrand.onclick = () => {
		if (flagViewData !== 'brand') {
			flagViewData = 'brand'
			loadButtonViewData()
			searchViewData()
		}
	}

	btnViewCategory.onclick = () => {
		if (flagViewData !== 'category') {
			flagViewData = 'category'
			loadButtonViewData()
			searchViewData()
		}
	}

	btnViewMonth.onclick = () => {
		if (!flagMonth) {
			flagMonth = true;
			loadForm()
		}
	}

	btnViewDay.onclick = () => {
		if (flagMonth) {
			flagMonth = false;
			loadForm()
		}
	}

	option.onchange = (event) => {
		const currentDate = new Date()
		switch (event.target.value) {
			case 'date':
				inputStartDate.value = formatDateYMD(currentDate)
				inputEndDate.value = formatDateYMD(currentDate)
				break;
			case 'week':
				const first = currentDate.getDate() - currentDate.getDay();
				const last = currentDate.getDate() + 6;
				inputStartDate.value = formatDateYMD(new Date(currentDate.setDate(first)))
				inputEndDate.value = formatDateYMD(new Date(currentDate.setDate(last)))
				break;
			case 'quarter-1st':
				inputStartDate.value = `${currentDate.getFullYear()}-01-01`
				inputEndDate.value = `${currentDate.getFullYear()}-04-01`
				break;
			case 'quarter-2st':
				inputStartDate.value = `${currentDate.getFullYear()}-04-01`
				inputEndDate.value = `${currentDate.getFullYear()}-07-01`
				break;
			case 'quarter-3st':
				inputStartDate.value = `${currentDate.getFullYear()}-07-01`
				inputEndDate.value = `${currentDate.getFullYear()}-10-01`
				break;
			case 'quarter-4st':
				inputStartDate.value = `${currentDate.getFullYear()}-10-01`
				inputEndDate.value = `${currentDate.getFullYear() + 1}-01-01`
				break;
			case 'full-year':
				inputStartDate.value = `${currentDate.getFullYear()}-01-01`
				inputEndDate.value = `${currentDate.getFullYear() + 1}-01-01`
				break;
		}
	}

	inputStartDate.onchange = (e) => {
		const startDate = new Date(e.target.value).getTime()
		const endDate = new Date(inputEndDate.value).getTime()
		if (startDate > endDate) {
			inputStartDate.value = inputEndDate.value
		}
		option.value = 'date'
	}

	inputEndDate.onchange = (e) => {
		const startDate = new Date(inputStartDate.value).getTime()
		const endDate = new Date(e.target.value).getTime()
		option.value = 'date'
		if (startDate > endDate) {
			inputEndDate.value = inputStartDate.value
		}
	}

	btnSearch.onclick = () => {
		searchViewData()
	}

	btnExpandAll.onclick = () => {
		const rows = $("#dashboard-grid").jqxTreeGrid('getRows');
		const traverseTree = (rows) => {
			for (let i = 0; i < rows.length; i++) {
				if (rows[i].records) {
					idValue = rows[i]["id"];
					$("#dashboard-grid").jqxTreeGrid("expandRow", idValue);
					traverseTree(rows[i].records);
				}
			}
		}
		traverseTree(rows)
	}

	btnCollapseAll.onclick = () => {
		const rows = $("#dashboard-grid").jqxTreeGrid('getRows');
		const traverseTree = (rows) => {
			for (let i = 0; i < rows.length; i++) {
				if (rows[i].records) {
					idValue = rows[i]["id"];
					$("#dashboard-grid").jqxTreeGrid("collapseAll", idValue);
					traverseTree(rows[i].records);
				}
			}
		}
		traverseTree(rows)
	}

	const refBtnViewData = () => {
		btnViewProduct.classList.remove('bg-gray-500')
		btnViewBrand.classList.remove('bg-gray-500')
		btnViewCategory.classList.remove('bg-gray-500')
		btnViewProduct.classList.add('bg-gray-400')
		btnViewBrand.classList.add('bg-gray-400')
		btnViewCategory.classList.add('bg-gray-400')
	}

	const loadForm = () => {
		const currentDate = new Date()
		if (flagMonth) {
			selectorYear.value = currentDate.getFullYear()
			selectorMonth.value = currentDate.getMonth() + 1
			formMonth.classList.remove('box-hidden')
			formDay.classList.add('box-hidden')
			btnViewMonth.classList.add('bg-gray-500')
			btnViewMonth.classList.remove('bg-gray-400')
			btnViewDay.classList.add('bg-gray-400')
			btnViewDay.classList.remove('bg-gray-500')
		} else {
			inputStartDate.value = formatDateYMD(currentDate)
			inputEndDate.value = formatDateYMD(currentDate)
			formMonth.classList.add('box-hidden')
			formDay.classList.remove('box-hidden')
			btnViewMonth.classList.add('bg-gray-400')
			btnViewMonth.classList.remove('bg-gray-500')
			btnViewDay.classList.add('bg-gray-500')
			btnViewDay.classList.remove('bg-gray-400')
		}
	}

	const loadButtonViewData = () => {
		refBtnViewData()
		switch (flagViewData) {
			case 'product':
				btnViewProduct.classList.remove('bg-gray-400')
				btnViewProduct.classList.add('bg-gray-500')
				btnExpandAll.classList.add('box-hidden')
				btnCollapseAll.classList.add('box-hidden')
				break;
			case 'brand':
				btnViewBrand.classList.remove('bg-gray-400')
				btnViewBrand.classList.add('bg-gray-500')
				btnExpandAll.classList.remove('box-hidden')
				btnCollapseAll.classList.remove('box-hidden')
				break;
			case 'category':
				btnViewCategory.classList.remove('bg-gray-400')
				btnViewCategory.classList.add('bg-gray-500')
				btnExpandAll.classList.remove('box-hidden')
				btnCollapseAll.classList.remove('box-hidden')
				break;
		}
	}

	const fetchDataMonth = async () => {
		return await fetch("/api/v1/product/statistics/month",
			{
				method: "POST",
				body: JSON.stringify(
					{
						year: selectorYear.value,
						month: selectorMonth.value
					})
				, headers: {
					'Content-Type': 'application/json'
				}
			}
		).then(data => data.json()).catch(e => console.log(e));
	}

	const fetchDataDay = async () => {
		return await fetch("/api/v1/product/statistics/day",
			{
				method: "POST",
				body: JSON.stringify(
					{
						startDate: inputStartDate.value,
						endDate: inputEndDate.value
					})
				, headers: {
					'Content-Type': 'application/json'
				}
			}
		).then(data => data.json()).catch(e => console.log(e));
	}

	const loadData = (data, hierarchy, options = []) => {
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
				{ name: 'brand', type: 'number' },
				{ name: 'categories', type: 'array' },
				...options
			],
			hierarchy: hierarchy,
			id: 'id',
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
					{
						text: '', dataField: 'a', width: 50,
					},
					{
						text: 'Xem', dataField: 'id', width: 80,
						cellsRenderer: (rowKey, dataField, value, data) => {
							if (data?.status) {
								return ''
							}
							return `
								<a href="/admin/formproduct?id=${data?.id}" 
								class="shadow bg-purple-500 hover:bg-purple-400 focus:shadow-outline focus:outline-none text-white font-bold py-2 px-4 rounded"
								>Xem</a>
							`
						}
					},
					{
						text: 'Tên', dataField: 'name', width: 200,
					},
					{
						text: 'Ảnh', dataField: 'image', width: 100,
						cellsRenderer: (rowKey, dataField, value, data) => {
							if (data?.status === 2) {
								return ''
							}
							return `
								<img src="/uploads/${data?.image}" style="width: 40px; height: 30px;" ></img>
							`
						}
					},
					{
						text: 'Thương hiệu', dataField: 'brand', width: 100,
						cellsRenderer: (rowKey, dataField, value, data) => {
							return data?.brand?.name
						}
					},
					{
						text: 'Danh mục', dataField: 'categories', width: 150,
						cellsRenderer: (rowKey, dataField, value, data) => {
							return data?.categories?.map(item => item.name)
						}
					},
					{ 
						text: 'Giá (VND)', dataField: 'price', width: 100,
						cellsRenderer: (rowKey, dataField, value, data) => {
							return enPriceVnd(value)
						}
					},
					{ text: 'Số lượng bán', dataField: 'quantityPrice', width: 110 },
					{ 
						text: 'Tổng tiền (VND)', dataField: 'totalPrice', width: 150,
						cellsRenderer: (rowKey, dataField, value, data) => {
							if (data?.status === 2) {
								return `<p class="text-red-800 text-[16px] font-bold">${value? enPriceVnd(value) : 0}</p>`
							}
							return `<p class="text-red-800 font-bold">${value? enPriceVnd(value) : 0}</p>`
						} 
					},
				]
			});
	}

	const fetchData = async () => {
		let data = []
		if (flagMonth) {
			data = await fetchDataMonth()
		} else {
			data = await fetchDataDay()
		}
		return data
	}

	const searchViewProduct = async () => {
		let data = await fetchData()
		let total = {
			id: 999999999,
			name: 'Tổng',
			image: '',
			price: '',
			quantityPrice: 0,
			totalPrice: 0,
			brand: '',
			categories: [],
			status: 2
		}
		data.map(item => {
			total.quantityPrice += item.quantityPrice
			total.totalPrice += item.totalPrice
			item.status = 0
		})
		data.push(total)
		loadData(data,
			{
				keyDataField: { name: 'brand' },
				parentDataField: { name: 'brand' }
			}, [{ name: 'status', type: 'int' },]
		)
	}

	const searchViewBrand = async () => {
		let data = await fetchData()
		let brands = []
		for (let product of data) {
			let flag = false
			for (let brand of brands) {
				if (product.brand.id === brand.id) {
					brand.quantityPrice += product.quantityPrice
					brand.totalPrice += product.totalPrice
					flag = true
					break
				}
			}
			if (!flag) {
				brands.push({
					...product.brand,
					price: '',
					quantityPrice: product.quantityPrice,
					totalPrice: product.totalPrice,
					brand: '',
					categories: [],
					brandId: product.brand.id,
				})
			}
		}
		brands.map(brand => {
			brand.status = 1
			brand.items = []
			for (let product of data) {
				if (brand.id === product.brand.id) {
					brand.items.push({
						...product,
						brandId: brand.id,
						products: []
					})
				}
			}
		})
		let total = {
			id: 999999999,
			name: 'Tổng',
			image: '',
			price: '',
			quantityPrice: 0,
			totalPrice: 0,
			brand: '',
			categories: [],
			status: 2
		}
		data.map(item => {
			total.quantityPrice += item.quantityPrice
			total.totalPrice += item.totalPrice
		})
		brands.push(total)
		loadData(brands,
			{ root: 'items' },
			[
				{ name: 'items', type: 'array' },
				{ name: 'status', type: 'int' },
			]
		)
	}

	const searchViewCategory = async () => {
		let data = await fetchData()
		let categories = []
		for (let product of data) {
			for (let categoryTmp of product.categories) {
				let flag = false
				for (let category of categories) {
					if (categoryTmp.id === category.id) {
						category.quantityPrice += product.quantityPrice
						category.totalPrice += product.totalPrice
						flag = true
					}
				}
				if (!flag) {
					categories.push({
						...categoryTmp,
						price: '',
						quantityPrice: product.quantityPrice,
						totalPrice: product.totalPrice,
						brand: '',
						categories: [],
						catgegoryId: categoryTmp.id,
					})
				}
			}
		}
		categories.map(category => {
			category.status = 1
			category.items = []
			for (let product of data) {
				for (let categoryTmp of product.categories) {
					if (category.id === categoryTmp.id) {
						category.items.push({
							...product,
							categoryId: categoryTmp.id,
							products: []
						})
					}
				}
			}
		})
		let total = {
			id: 999999999,
			name: 'Tổng',
			image: '',
			price: '',
			quantityPrice: 0,
			totalPrice: 0,
			brand: '',
			categories: [],
			status: 2
		}
		data.map(item => {
			total.quantityPrice += item.quantityPrice
			total.totalPrice += item.totalPrice
		})
		categories.push(total)
		loadData(categories,
			{ root: 'items' },
			[
				{ name: 'items', type: 'array' },
				{ name: 'status', type: 'int' },
			]
		)
	}

	const searchViewData = () => {
		switch (flagViewData) {
			case 'product':
				searchViewProduct()
				break;
			case 'brand':
				searchViewBrand()
				break;
			case 'category':
				searchViewCategory()
				break;
		}
	}

	loadForm()
	loadButtonViewData()
	searchViewData()
})


