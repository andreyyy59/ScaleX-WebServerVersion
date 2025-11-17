// Funcionalidades específicas para la página de comparación
document.addEventListener('DOMContentLoaded', function() {
    initializeComparisonTable();
    initializePrintFunctionality();
});

function initializeComparisonTable() {
    // Resaltar diferencias significativas
    const table = document.querySelector('.comparison-table table');
    if (!table) return;
    
    const rows = table.querySelectorAll('tbody tr');
    
    rows.forEach(row => {
        const cells = row.querySelectorAll('td');
        if (cells.length < 2) return;
        
        const values = Array.from(cells).slice(1).map(cell => {
            const text = cell.textContent.trim();
            return isNaN(text) ? text : parseFloat(text);
        });
        
        // Solo procesar valores numéricos
        if (values.every(val => typeof val === 'number')) {
            const max = Math.max(...values);
            const min = Math.min(...values);
            
            // Resaltar si hay diferencias significativas (>10%)
            if (max > 0 && (max - min) / max > 0.1) {
                cells.forEach((cell, index) => {
                    if (index > 0) {
                        const value = values[index - 1];
                        if (value === max) {
                            cell.style.backgroundColor = '#dcfce7'; // Verde para el mejor
                            cell.title = 'Mejor valor';
                        } else if (value === min) {
                            cell.style.backgroundColor = '#fef2f2'; // Rojo para el peor
                            cell.title = 'Valor más bajo';
                        }
                    }
                });
            }
        }
    });
}

function initializePrintFunctionality() {
    // Mejorar la impresión
    const printBtn = document.querySelector('.btn-primary[onclick*="print"]');
    if (printBtn) {
        printBtn.addEventListener('click', function() {
            // Agregar estilos para impresión
            const printStyles = `
                @media print {
                    .header, .footer, .comparison-actions { 
                        display: none !important; 
                    }
                    body { 
                        font-size: 12pt; 
                    }
                    .comparison-table table {
                        width: 100%;
                        border-collapse: collapse;
                    }
                    .comparison-table th,
                    .comparison-table td {
                        border: 1px solid #000;
                        padding: 8px;
                    }
                }
            `;
            
            const styleSheet = document.createElement("style");
            styleSheet.type = "text/css";
            styleSheet.innerText = printStyles;
            document.head.appendChild(styleSheet);
            
            setTimeout(() => {
                window.print();
                // Remover estilos después de imprimir
                styleSheet.remove();
            }, 500);
        });
    }
}

// Exportar datos de comparación
function exportComparison() {
    const table = document.querySelector('.comparison-table table');
    if (!table) return;
    
    let csv = [];
    const rows = table.querySelectorAll('tr');
    
    rows.forEach(row => {
        const rowData = [];
        const cells = row.querySelectorAll('th, td');
        
        cells.forEach(cell => {
            rowData.push(cell.textContent.trim());
        });
        
        csv.push(rowData.join(','));
    });
    
    const csvContent = "data:text/csv;charset=utf-8," + csv.join('\n');
    const encodedUri = encodeURI(csvContent);
    const link = document.createElement("a");
    link.setAttribute("href", encodedUri);
    link.setAttribute("download", "comparacion_motos.csv");
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}