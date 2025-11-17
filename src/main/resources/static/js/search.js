// search.js - Funcionalidad de búsqueda y filtros

document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const searchBtn = document.getElementById('searchBtn');
    const categoryFilter = document.getElementById('categoryFilter');
    const makeFilter = document.getElementById('makeFilter');
    const motorcyclesGrid = document.getElementById('motorcyclesGrid');
    
    // Guardar todas las motos originales
    let allMotorcycles = [];
    
    // Obtener todas las tarjetas de motos al cargar
    if (motorcyclesGrid) {
        allMotorcycles = Array.from(motorcyclesGrid.querySelectorAll('.motorcycle-card'));
    }
    
    // Función de búsqueda
    function searchMotorcycles() {
        const searchTerm = searchInput ? searchInput.value.toLowerCase().trim() : '';
        const selectedCategory = categoryFilter ? categoryFilter.value : '';
        const selectedMake = makeFilter ? makeFilter.value : '';
        
        allMotorcycles.forEach(card => {
            const make = card.querySelector('h3')?.textContent.toLowerCase() || '';
            const category = card.querySelector('.category')?.textContent.toLowerCase() || '';
            
            // Verificar si coincide con los filtros
            const matchesSearch = searchTerm === '' || make.includes(searchTerm);
            const matchesCategory = selectedCategory === '' || category.includes(selectedCategory.toLowerCase());
            const matchesMake = selectedMake === '' || make.includes(selectedMake.toLowerCase());
            
            // Mostrar u ocultar la tarjeta
            if (matchesSearch && matchesCategory && matchesMake) {
                card.style.display = 'block';
                card.style.animation = 'fadeIn 0.5s ease-out';
            } else {
                card.style.display = 'none';
            }
        });
        
        // Verificar si hay resultados
        checkNoResults();
    }
    
    // Verificar si no hay resultados
    function checkNoResults() {
        const visibleCards = allMotorcycles.filter(card => card.style.display !== 'none');
        
        // Eliminar mensaje anterior si existe
        const existingMessage = motorcyclesGrid.querySelector('.no-results-message');
        if (existingMessage) {
            existingMessage.remove();
        }
        
        // Si no hay resultados, mostrar mensaje
        if (visibleCards.length === 0) {
            const noResultsDiv = document.createElement('div');
            noResultsDiv.className = 'no-results-message';
            noResultsDiv.style.gridColumn = '1 / -1';
            noResultsDiv.style.textAlign = 'center';
            noResultsDiv.style.padding = '3rem';
            noResultsDiv.style.background = 'rgba(255, 255, 255, 0.1)';
            noResultsDiv.style.borderRadius = '20px';
            noResultsDiv.style.border = '2px solid rgba(220, 38, 38, 0.2)';
            noResultsDiv.innerHTML = `
                <i class="fas fa-search" style="font-size: 3rem; margin-bottom: 1rem; opacity: 0.5;"></i>
                <h3>No se encontraron resultados</h3>
                <p>Intenta con otros términos de búsqueda o filtros</p>
            `;
            motorcyclesGrid.appendChild(noResultsDiv);
        }
    }
    
    // Event listeners
    if (searchInput) {
        searchInput.addEventListener('input', searchMotorcycles);
        searchInput.addEventListener('keypress', function(e) {
            if (e.key === 'Enter') {
                e.preventDefault();
                searchMotorcycles();
            }
        });
    }
    
    if (searchBtn) {
        searchBtn.addEventListener('click', searchMotorcycles);
    }
    
    if (categoryFilter) {
        categoryFilter.addEventListener('change', searchMotorcycles);
    }
    
    if (makeFilter) {
        makeFilter.addEventListener('change', searchMotorcycles);
    }
    
    // Funcionalidad de favoritos
    const favoriteButtons = document.querySelectorAll('.favorite-btn');
    
    favoriteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            e.stopPropagation();
            
            const motorcycleId = this.getAttribute('data-id');
            const icon = this.querySelector('i');
            
            // Toggle estado de favorito
            if (icon.classList.contains('far')) {
                icon.classList.remove('far');
                icon.classList.add('fas');
                this.classList.add('active');
                
                // Guardar en localStorage
                addToFavorites(motorcycleId);
                
                // Mostrar notificación
                showNotification('Agregado a favoritos', 'success');
            } else {
                icon.classList.remove('fas');
                icon.classList.add('far');
                this.classList.remove('active');
                
                // Eliminar de localStorage
                removeFromFavorites(motorcycleId);
                
                // Mostrar notificación
                showNotification('Eliminado de favoritos', 'info');
            }
        });
    });
    
    // Funciones de favoritos con localStorage
    function addToFavorites(id) {
        let favorites = JSON.parse(localStorage.getItem('favorites') || '[]');
        if (!favorites.includes(id)) {
            favorites.push(id);
            localStorage.setItem('favorites', JSON.stringify(favorites));
        }
    }
    
    function removeFromFavorites(id) {
        let favorites = JSON.parse(localStorage.getItem('favorites') || '[]');
        favorites = favorites.filter(favId => favId !== id);
        localStorage.setItem('favorites', JSON.stringify(favorites));
    }
    
    function loadFavorites() {
        const favorites = JSON.parse(localStorage.getItem('favorites') || '[]');
        favorites.forEach(id => {
            const button = document.querySelector(`.favorite-btn[data-id="${id}"]`);
            if (button) {
                const icon = button.querySelector('i');
                icon.classList.remove('far');
                icon.classList.add('fas');
                button.classList.add('active');
            }
        });
    }
    
    // Cargar favoritos al inicio
    loadFavorites();
    
    // Funcionalidad de comparar
    let compareList = [];
    const maxCompare = 3;
    
    const compareButtons = document.querySelectorAll('.btn-compare');
    
    compareButtons.forEach(button => {
        button.addEventListener('click', function() {
            const motorcycleId = this.getAttribute('data-id');
            
            if (compareList.includes(motorcycleId)) {
                // Eliminar de la lista
                compareList = compareList.filter(id => id !== motorcycleId);
                this.textContent = 'Comparar';
                this.style.background = '#dc2626';
            } else {
                // Agregar a la lista
                if (compareList.length >= maxCompare) {
                    showNotification(`Solo puedes comparar hasta ${maxCompare} motos`, 'warning');
                    return;
                }
                compareList.push(motorcycleId);
                this.textContent = 'Seleccionada ✓';
                this.style.background = '#16a34a';
            }
            
            // Actualizar botón de comparar global si existe
            updateCompareButton();
        });
    });
    
    function updateCompareButton() {
        let globalCompareBtn = document.getElementById('globalCompareBtn');
        
        if (compareList.length > 0 && !globalCompareBtn) {
            // Crear botón flotante de comparar
            globalCompareBtn = document.createElement('button');
            globalCompareBtn.id = 'globalCompareBtn';
            globalCompareBtn.className = 'floating-compare-btn';
            globalCompareBtn.innerHTML = `
                <i class="fas fa-balance-scale"></i>
                Comparar (${compareList.length})
            `;
            globalCompareBtn.style.cssText = `
                position: fixed;
                bottom: 2rem;
                right: 2rem;
                background: #dc2626;
                color: white;
                border: none;
                padding: 1rem 2rem;
                border-radius: 50px;
                font-weight: 600;
                font-size: 1rem;
                cursor: pointer;
                box-shadow: 0 10px 30px rgba(220, 38, 38, 0.4);
                z-index: 999;
                transition: all 0.3s ease;
            `;
            
            globalCompareBtn.addEventListener('click', function() {
                if (compareList.length >= 2) {
                    window.location.href = `/compare?ids=${compareList.join(',')}`;
                } else {
                    showNotification('Selecciona al menos 2 motos para comparar', 'warning');
                }
            });
            
            globalCompareBtn.addEventListener('mouseenter', function() {
                this.style.transform = 'scale(1.05)';
                this.style.boxShadow = '0 15px 40px rgba(220, 38, 38, 0.5)';
            });
            
            globalCompareBtn.addEventListener('mouseleave', function() {
                this.style.transform = 'scale(1)';
                this.style.boxShadow = '0 10px 30px rgba(220, 38, 38, 0.4)';
            });
            
            document.body.appendChild(globalCompareBtn);
        } else if (compareList.length > 0 && globalCompareBtn) {
            // Actualizar contador
            globalCompareBtn.innerHTML = `
                <i class="fas fa-balance-scale"></i>
                Comparar (${compareList.length})
            `;
        } else if (compareList.length === 0 && globalCompareBtn) {
            // Eliminar botón
            globalCompareBtn.remove();
        }
    }
    
    // Función para mostrar notificaciones
    function showNotification(message, type = 'info') {
        const notification = document.createElement('div');
        notification.className = `notification notification-${type}`;
        notification.textContent = message;
        notification.style.cssText = `
            position: fixed;
            top: 2rem;
            right: 2rem;
            background: ${type === 'success' ? '#16a34a' : type === 'warning' ? '#eab308' : '#3b82f6'};
            color: white;
            padding: 1rem 1.5rem;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            z-index: 9999;
            animation: slideInRight 0.3s ease-out;
            font-weight: 600;
        `;
        
        document.body.appendChild(notification);
        
        setTimeout(() => {
            notification.style.animation = 'slideOutRight 0.3s ease-out';
            setTimeout(() => notification.remove(), 300);
        }, 3000);
    }
});

// Animaciones CSS adicionales
const style = document.createElement('style');
style.textContent = `
    @keyframes slideInRight {
        from {
            transform: translateX(400px);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }
    
    @keyframes slideOutRight {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(400px);
            opacity: 0;
        }
    }
`;
document.head.appendChild(style);