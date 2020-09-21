import { Directive, ElementRef, HostListener, Renderer2, Input } from '@angular/core';

@Directive({
  selector: '[appModal]'
})
export class ModalDirective {

  @Input() appModal;

  constructor(private renderer: Renderer2, private el: ElementRef) { }

  @HostListener('click', ['$event']) toggleOpen(event: MouseEvent) {

    if (this.appModal.classList.contains('in') === false) {
      this.renderer.addClass(this.appModal, 'in');
      this.renderer.setStyle(this.appModal, 'display', 'block');
    }
    else {
      this.renderer.removeClass(this.appModal, 'in');
      this.renderer.setStyle(this.appModal, 'display', 'none');
    }
  }

}
