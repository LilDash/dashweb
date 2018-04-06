import * as React from 'react';
import PropTypes from 'prop-types';
import './nav-grid.scss';

import { NavGridCell } from './NavGridCell';


class NavGrid extends React.Component {

	constructor(props) {
	    super(props);
	    //this.imageOnLoad = this.imageOnLoad.bind(this);
	    this.state = {
		  //isImageDisplayed: false,
		  
	    };
	}

	navGridRow(cells, index) {
		return (
			<div className='nav-grid-row' key={`nav-grid-row-${index}`}>
				{ 
					cells.map((y, j) => 
						<NavGridCell 
							key={`nav-grid-cell-${y.name}`} 
							{...y} 
							isActive={this.props.activeItem === y.categoryId}
							onCellClick={this.props.onCellClick} 
						/>	
					)
				}
			</div>
		);
	}

	render() {
		return (
			<div className='nav-grid' >
				{ 
					this.props.navGrid.map((x, i) => 
						this.navGridRow(x, i)
					)
				}
			</div>
		);
	}
};

NavGrid.propTypes = {
	navGrid: PropTypes.array.isRequired,
	activeItem: PropTypes.number.isRequired,
	onCellClick: PropTypes.func.isRequired,  
};

NavGrid.defaultProps = {
  	
};

export { NavGrid };