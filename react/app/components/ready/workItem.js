import React, {Component} from 'react';
import WorkitemUpdate from "./workitemUpdate";

export default class WorkItem extends Component {
  constructor(props) {
    super(props);
    this.state = {
      workitem: this.props.workitem,
      update: false
    }
    this.toggleUpdate = this.toggleUpdate.bind(this);
  }

  updateOnServer() {

  }

  renderWorkitemUpdate() {
    if (this.state.update) {
      return <WorkitemUpdate onFinish={this.toggleUpdate}
                             workitem={this.state.workitem}
                             masters={this.props.masters}/>;
    }
  }

  toggleUpdate() {
    this.setState({update: !this.state.update});
  }

  render() {
    const {comment, service_time, procedures, client_detales} = this.state.workitem;
    const commentBlock = comment
        ? <div className="comment">{comment}</div>
        : null;
    return (
        <div className={"workitem"}>
          <div>
            <div>
              <span className="time">{service_time}-</span>
              {
                procedures.map((item, i) =>
                    <span className="time" key={i}>{item.procedure_name}.</span>
                )
              }
              <span
                  className="time">({client_detales.name},[{client_detales.phones}]).</span>
            </div>
            {commentBlock}
          </div>
          <button className={"update_workitem"} onClick={this.toggleUpdate}>
            Update
          </button>
          {this.renderWorkitemUpdate()}
        </div>
    )
  }
}
