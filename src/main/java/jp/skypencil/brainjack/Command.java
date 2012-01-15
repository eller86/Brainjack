package jp.skypencil.brainjack;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Maps;

abstract class Command {
	private static Map<Byte, Command> map;
	static Command[] values() {
		return new Command[] {
				new IncrementData(),
				new IncrementDataPointer(),
				new Accept(),
				new DecrementData(),
				new DecrementDataPointer(),
				new EndLoop(),
				new Output(),
				new StartLoop()
		};
	}

	static {
		map = Maps.newHashMap();
		for (Command command : values()) {
			map.put(command.getCharacter(), command);
		}
	}

	@Nullable
	static Command fromByte(@Nonnegative byte byteData) {
		return map.get(Byte.valueOf(byteData));
	}

	static class IncrementDataPointer extends Command {
		@Override
		byte getCharacter() {
			return '>';
		}

		@Override
		void accept(Visitor visitor) {
			visitor.visit(this);
		}
	};
	static class  DecrementDataPointer extends Command {
		@Override
		byte getCharacter() {
			return '<';
		}

		@Override
		void accept(Visitor visitor) {
			visitor.visit(this);
		}
	};
	static class IncrementData extends Command {
		@Override
		byte getCharacter() {
			return '+';
		}

		@Override
		void accept(Visitor visitor) {
			visitor.visit(this);
		}
	};
	static class DecrementData extends Command {
		@Override
		byte getCharacter() {
			return '-';
		}

		@Override
		void accept(Visitor visitor) {
			visitor.visit(this);
		}
	};
	static class Output extends Command {
		@Override
		byte getCharacter() {
			return '.';
		}

		@Override
		void accept(Visitor visitor) throws IOException {
			visitor.visit(this);
		}
	};
	static class Accept extends Command {
		@Override
		byte getCharacter() {
			return ',';
		}

		@Override
		void accept(Visitor visitor) throws IOException {
			visitor.visit(this);
		}
	};
	static class StartLoop extends Command {
		@Override
		byte getCharacter() {
			return CHAR_START_LOOP;
		}

		@Override
		void accept(Visitor visitor) {
			visitor.visit(this);
		}
	};
	static class EndLoop extends Command {
		@Override
		byte getCharacter() {
			return CHAR_END_LOOP;
		}

		@Override
		void accept(Visitor visitor) {
			visitor.visit(this);
		}
	};

	static final char CHAR_START_LOOP = '[';
	static final char CHAR_END_LOOP = ']';

	@Nonnegative
	abstract byte getCharacter();
	abstract void accept(@Nonnull Visitor visitor) throws IOException;
}
