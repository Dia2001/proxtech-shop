(() => {
	let filter_col = document.querySelector('#filter-col')

	document.querySelector('#filter-toggle').addEventListener('click', () => filter_col.classList.toggle('active'))
	
	document.querySelector('#filter-close').addEventListener('click', () => filter_col.classList.toggle('active'))

	let filterChoise = []
	let btnView = document.getElementById('btnView')
	let btnPrev = document.getElementById('btnPrev')
	let btnNext = document.getElementById('btnNext')
	let linkPages = Array.from(document.querySelectorAll('[id^="p-"]'))
	let sortSelect = document.getElementById("sort")
	
	let paramString = document.location.search.split('?')[1]
	let queryString = new URLSearchParams(paramString)
	
	let page = currentPage
	let listBrandIds = queryString.get('b') || null
	let categoryId = queryString.get('c') || null
	let startPrice = queryString.get('sp') || null
	let endPrice = queryString.get('ep') || null
	let sort = queryString.get('s') || null
	let search = queryString.get('q') || null
	let attributes = []
	
	queryString.forEach((v, k) => {
	  if (k.startsWith('a')) {
	    attributes.push({k, v})
	  }
	})
	
	const loadFilter = () => {
		if (listBrandIds) {
			listBrandIds.split(',').map(item => {
				let brandInput = document.getElementById("b-" + item)
				if (brandInput) {
					brandInput.checked = true
				}
				let brandLink = document.getElementById("b-link-" + item)
				if (brandLink){
					brandLink.classList.add("brand-link-active")
				}
			})
		}
		if (startPrice) {
	  		let startPriceInput = document.getElementById('sp') 
	  		if (startPriceInput) {
				startPriceInput.value = startPrice  
			}
		}
		if (endPrice) {
	  		let endPriceInput = document.getElementById('ep') 
	  		if (endPriceInput) {
				endPriceInput.value = endPrice  
			}
		}
		attributes.map(item => {
			let attributeSelect = document.getElementById("a-" + item.k.slice(1,2))
			if (attributeSelect) {
				attributeSelect.value = item.v
			}
		})
		if (sort) {
			if (sortSelect) {
				sortSelect.value = sort
			}
		}
		if (search) {
			let searchInput = document.getElementById("search-input")
			if (searchInput) {
				searchInput.value = search
			}
		}
	}
	
	loadFilter()
	
	const acceptFilter = () => {
	  let paramString = '?'
	  paramString += `p=${page}`
	  paramString += search? `&q=${search}` : ''
	  paramString += listBrandIds? `&b=${listBrandIds}` : ''
	  paramString += categoryId? `&c=${categoryId}` : ''
	  paramString += startPrice? `&sp=${startPrice}` : ''
	  paramString += endPrice? `&ep=${endPrice}` : ''
	  paramString += sort? `&s=${sort}` : ''
	
	  if (attributes.length > 0) {
	    attributes.forEach(item => {
	      paramString += `&${item.k}=${item.v}`
	    })
	  }
	  window.location = document.location.pathname + paramString
	}
	
	if (sortSelect) {
		sortSelect.onchange = (e) => {
			sort = e.target.value
		    acceptFilter()
		} 
	}
	
	if (btnPrev) {
	  btnPrev.onclick = (e) => {
	    e.preventDefault()
	    page = currentPage - 1 
	    acceptFilter()
	  }
	}
	
	if (btnNext) {
	  btnNext.onclick = (e) => {
	    e.preventDefault()
	    page = currentPage + 1
	    acceptFilter()
	  } 
	}
	
	linkPages.forEach(item => {
	  item.onclick = (e) => {
	    e.preventDefault()
	    page = item.id.split("p-")[1]
	    acceptFilter()
	  }
	})
	
	btnView.onclick = () => {
	  listBrandIds = Array.from(document.querySelectorAll('input[id^="b-"]:checked')).map(item => {
	    return item.id.split("-")[1]
	  }).toString()
	  let sPrice = document.getElementById('sp').value
	  let ePrice = document.getElementById('ep').value
	  if (sPrice != "" || sPrice != 0) {
	    startPrice = sPrice
	  }
	  if (ePrice != "" || ePrice != 0) {
	    endPrice = ePrice
	  }
	  attributes = Array.from(document.querySelectorAll('select[id^="a-"]'))
	                        .filter(item => item.value != '')
	                        .map(item => ({
	                          k: item.id.split("-")[0] + item.id.split("-")[1],
	                          v: item.value
	                        }))
	  acceptFilter()
	}
})()
