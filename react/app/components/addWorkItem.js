import React from 'react';
import ReactDOM from 'react-dom';

class AddWorkItemWidjet extends React.Component{
  constructor(props){
      super(props);
      this.state={add:this.props.data.add, date:this.props.date, data:this.props.data};
      this.renderProceduresList=this.renderProceduresList.bind(this);
  }

  renderProceduresList(item){
      return <button>{item}</button>;
  }
  render(){
      if(this.state.add.show)
          return <div className="add-widjet">
              {this.state.data.data.procedures
                  .sort((a,b)=>{
                      if(a.name < b.name)
                          return -1;
                      if(a.name > b.name)
                          return 1;
                  })
                  .map(item=>this.renderProceduresList(item.name))}
          </div>;
      else
          return "";
  }
}