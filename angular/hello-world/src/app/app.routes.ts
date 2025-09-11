import {Routes} from '@angular/router';
import {TemplateDrivenComponent} from './forms/template-driven/template-driven.component';
import {TwoWayDataBindingComponent} from './data-binding/two-way-data-binding/two-way-data-binding.component';
import {InterpolationComponent} from './data-binding/interpolation/interpolation.component';
import {EventBindingComponent} from './data-binding/event-binding/event-binding.component';
import {PropertyBindingComponent} from './data-binding/property-binding/property-binding.component';
import {HelloWorldComponent} from './hello-world/hello-world.component';
import {ButtonComponent} from './angular-material/button/button.component';
import {FormFieldComponent} from './angular-material/form-field/form-field.component';

export const routes: Routes = [
  {path: "", component: HelloWorldComponent},
  {path: "interpolation", component: InterpolationComponent},
  {path: "property-binding", component: PropertyBindingComponent},
  {path: "event-binding", component: EventBindingComponent},
  {path: "two-way-data-binding", component: TwoWayDataBindingComponent},
  {path: "template-drive", component: TemplateDrivenComponent},
  {path: "button", component: ButtonComponent},
  {path: "formField", component: FormFieldComponent},
];
